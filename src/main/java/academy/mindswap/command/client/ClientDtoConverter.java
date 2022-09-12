package academy.mindswap.command.client;

import academy.mindswap.model.Client;

public class ClientDtoConverter {

    public static Client fromCreateDto(CreateClientDto createClientDto) {
        return Client.builder()
                .id(createClientDto.getId())
                .name(createClientDto.getName())
                .email(createClientDto.getEmail())
                .pin(createClientDto.getPin())
                .build();
    }

    public static ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }


}
