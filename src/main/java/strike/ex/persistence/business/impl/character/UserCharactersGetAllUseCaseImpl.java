    package strike.ex.persistence.business.impl.character;

    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Service;
    import strike.ex.persistence.business.UserCharactersGetAllUseCase;
    import strike.ex.persistence.domain.UserCharacterDTO;
    import strike.ex.persistence.entity.UserCharactersEntity;
    import strike.ex.persistence.repository.UserCharactersRepository;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @AllArgsConstructor
    public class UserCharactersGetAllUseCaseImpl implements UserCharactersGetAllUseCase {

        private final UserCharactersRepository userCharactersRepository;

        @Override
        public List<UserCharacterDTO> getAllUserCharacters(long id) {
            List<UserCharactersEntity> charactersEntities = userCharactersRepository.findAllByUser_Id(id);
            return charactersEntities.stream()
                    .map(UserCharacterDTOConverter::mapToUserCharacterDTO)
                    .collect(Collectors.toList());
        }

    }



