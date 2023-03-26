package com.pitayafruit.chatcc.api.domain.ai.model.aggregates;

import com.pitayafruit.chatcc.api.domain.ai.model.vo.Choices;
import lombok.Data;

import java.util.List;

/**
 * Ai Answer
 */
@Data
public class AIAnswer {

    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;
}
