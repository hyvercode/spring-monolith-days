package com.hyvercode.monolithdays.helpers.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements Serializable {}