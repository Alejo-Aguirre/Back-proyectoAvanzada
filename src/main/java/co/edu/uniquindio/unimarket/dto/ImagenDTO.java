package co.edu.uniquindio.unimarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagenDTO {

    private String idImagen;

    private String url;

    public ImagenDTO(String url) {
        this.url = url;
    }

}
