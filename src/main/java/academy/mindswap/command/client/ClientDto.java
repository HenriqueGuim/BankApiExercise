package academy.mindswap.command.client;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ClientDto {
    private  Long id;
    private  String name;
    private  String email;


}