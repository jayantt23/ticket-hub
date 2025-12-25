package com.jayant.catalog_service.service.impl;

import com.jayant.catalog_service.dto.ScheduleShowRequest;
import com.jayant.catalog_service.dto.ShowDetailDto;
import com.jayant.catalog_service.dto.ShowResponseDto;
import com.jayant.catalog_service.entity.Hall;
import com.jayant.catalog_service.entity.Movie;
import com.jayant.catalog_service.entity.Show;
import com.jayant.catalog_service.mapper.ShowMapper;
import com.jayant.catalog_service.repository.HallRepository;
import com.jayant.catalog_service.repository.MovieRepository;
import com.jayant.catalog_service.repository.ShowRepository;
import com.jayant.catalog_service.service.ShowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    private final MovieRepository movieRepository;

    private final HallRepository hallRepository;

    private final ShowMapper mapper;

    @Override
    @Transactional
    public ShowResponseDto saveShow(ScheduleShowRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + request.getMovieId()));

        Hall hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found with id: " + request.getHallId()));

        boolean isOccupied = showRepository.existsByHallIdAndStartTimeBetween(
                request.getHallId(),
                request.getStartTime().minusHours(2),
                request.getStartTime().plusHours(2)
        );

        if (isOccupied) {
            throw new RuntimeException("Hall is already booked for this time slot!");
        }

        Show show = new Show();
        show.setMovie(movie);
        show.setHall(hall);
        show.setStartTime(request.getStartTime());
        show.setBasePrice(request.getBasePrice());

        Show savedShow = showRepository.save(show);

        return mapper.toResponseDto(savedShow);
    }

    @Override
    public List<ShowResponseDto> getAllShows(String city, Long movieId, LocalDate date) {
        LocalDate searchDate = (date != null) ? date : LocalDate.now();

        LocalDateTime startOfDay = searchDate.atStartOfDay();
        LocalDateTime endOfDay = searchDate.atTime(LocalTime.MAX);

        List<Show> shows = showRepository.findShowsWithDetails(city, movieId, startOfDay, endOfDay);

        return shows.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ShowDetailDto getShow(Long id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));

        return mapper.toDetailsDto(show);
    }
}
