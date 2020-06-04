package com.gangs.apple.viewmodel;

import org.modelmapper.ModelMapper;

import com.gangs.apple.utility.ModelMapperSingle;

import lombok.Data;

@Data
public class BaseVM {
    protected static ModelMapper modelMapper = ModelMapperSingle.Instance();


}
