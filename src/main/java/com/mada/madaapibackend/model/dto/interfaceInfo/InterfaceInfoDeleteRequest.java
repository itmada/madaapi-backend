package com.mada.madaapibackend.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceInfoDeleteRequest implements Serializable {
    private Long id;
    private static final long serialVersionUID = 76530879092986245L;
}
