package com.pitayafruit.chatcc.api.domain.ai;

import java.io.IOException;

/**
 * chatGPT open-ai接口
 */
public interface IOpenAI {

    String doChatGPT(String question) throws IOException;
}
