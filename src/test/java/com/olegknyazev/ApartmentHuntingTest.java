package com.olegknyazev;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olegknyazev.ApartmentHunting.findBestBlock;
import static org.assertj.core.api.Assertions.assertThat;

class ApartmentHuntingTest {
    @Test
    void forEmptyInput_returnsMinusOne() {
        assertThat(findBestBlock(blocks(), requirements("gym"))).isEqualTo(-1);
    }

    @Test
    void ifBlocksDoNotHaveAllTheAmenities_returnsMaxValue() {
        assertThat(findBestBlock(blocks(block("gym", "cinema")), requirements("cinema", "mall"))).isEqualTo(-1);
    }

    @Test
    void ifBlocksHaveAllTheAmenities_returnsTheBestOption() {
        assertThat(
                findBestBlock(
                        blocks(
                                block("school"),
                                block("gym"),
                                block("gym", "school"),
                                block("school"),
                                block("school", "store")
                        ),
                        requirements("gym", "school", "store")
                )
        ).isEqualTo(3);
    }

    @SafeVarargs
    private static List<Map<String, Boolean>> blocks(Map<String, Boolean>... items) {
        return List.of(items);
    }

    private static Map<String, Boolean> block(String... amenities) {
        return Arrays.stream(amenities).collect(Collectors.toMap(Function.identity(), (x) -> true));
    }

    private static String[] requirements(String... amenities) {
        return amenities;
    }
}