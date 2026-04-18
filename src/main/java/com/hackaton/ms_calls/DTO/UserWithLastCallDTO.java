package com.hackaton.ms_calls.DTO;

import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.models.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserWithLastCallDTO {
    private User user;
    private Call lastCall;
}