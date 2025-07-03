package com.tracker.analytics.service;

import com.tracker.analytics.dto.DataDto;
import com.tracker.analytics.dto.HabitDto;
import com.tracker.analytics.exceptions.EntityNotFoundException;
import com.tracker.analytics.repository.DataRedisRepository;
import com.tracker.analytics.repository.HabitRedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitService {
    private final HabitRedisRepository habitRedisRepository;
    private final DataRedisRepository dataRedisRepository;

    public void saveHabit(HabitDto habitDto, DataDto descriptionsDto) {
        habitRedisRepository.save(habitDto, habitDto.getId());

        if (descriptionsDto != null) {
            dataRedisRepository.save(descriptionsDto, habitDto.getId());
        }
    }

    private List<HabitDto> getHabitsByExistingType(Set<Long> ids, String type) {
        List<Long> habits = habitRedisRepository
                                .findHabitsByType(type)
                                .stream()
                                .map(HabitDto::getId)
                                .toList();

        return ids
                .stream()
                .filter(habits::contains)
                .map(habitRedisRepository::findById)
                .toList();
    }

    private List<HabitDto> getHabitsByType(Set<Long> ids, String type) {
        if (type == null) {
            return ids
                    .stream()
                    .map(habitRedisRepository::findById)
                    .toList();
        }

        return getHabitsByExistingType(ids, type);
    }

    public List<HabitDto> getAllHabitsOfUser(long userId, String type) {
        Map<Long, List<Byte>> data = dataRedisRepository.getHabitData(userId);

        if (data.isEmpty()) return List.of();

        List<HabitDto> habits = getHabitsByType(data.keySet(), type);

        habits.forEach(h -> {
            List<Byte> habitData = data.get(h.getId());

            byte currentWeekProgress = habitData.get(0);

            byte targetPerWeek = habitData.get(1);

            if (h.getTargetPerWeek() != targetPerWeek) {
                h.setTargetPerWeek(targetPerWeek);
            }

            if (h.getCurrentWeekProgress() != currentWeekProgress) {
                h.setCurrentWeekProgress(currentWeekProgress);
            }
        });

        return habits;
    }

    public void deleteHabit(long habitId) {
        habitRedisRepository.deleteHabitById(habitId);
    }

    public HabitDto getHabitByIdAndType(long userId, long habitId, String type) {
        List<HabitDto> habits = getAllHabitsOfUser(userId, type);

        return habits
                .stream()
                .filter(h -> h.getId() == habitId)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Long> getUserIds() {
        return dataRedisRepository
                .findAll()
                .stream()
                .flatMap(d -> d.userIdsData().keySet().stream())
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();

    }
}
