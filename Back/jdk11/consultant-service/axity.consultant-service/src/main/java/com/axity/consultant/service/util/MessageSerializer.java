package com.axity.consultant.service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.axity.consultant.commons.request.MessageDto;

/**
 * Message Serializer class
 * 
 * @author KevinTellez@axity.com
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
