package com.gk.gourmet_note_storage.vo;

import lombok.Builder;

@Builder
public record ResponseFile(
        String originName,
        String saveName
) {
}
