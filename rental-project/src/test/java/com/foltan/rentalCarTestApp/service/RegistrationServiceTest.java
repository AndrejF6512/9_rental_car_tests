package com.foltan.rentalCarTestApp.service;

import com.foltan.rentalCarTestApp.domain.User;
import com.foltan.rentalCarTestApp.dto.UserInDto;
import com.foltan.rentalCarTestApp.exception.ExistingEntityException;
import com.foltan.rentalCarTestApp.exception.WeakPasswordException;
import com.foltan.rentalCarTestApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

        @Mock
        UserRepository userRepository;

        @InjectMocks
        RegistrationService registrationService;

        @Test
        void itShouldThrowExistingEntityException() {
                UserInDto userInDto = UserInDto.builder()
                        .username("MalyPavol123")
                        .build();

                User user = User.builder()
                        .username("MalyPavol123")
                        .build();


                when(userRepository.findByUsername(userInDto.getUsername())).thenReturn(Optional.of(user));


                assertThrows(ExistingEntityException.class, () -> registrationService.registerUser(userInDto));
        }

        @Test
        void itShouldThrowWeakPasswordException() {
                UserInDto userInDto = UserInDto.builder()
                        .username("JanABC685")
                        .password("jancde56")
                        .build();


                when(userRepository.findByUsername(userInDto.getUsername())).thenReturn(Optional.empty());


                assertThrows(WeakPasswordException.class, () -> registrationService.registerUser(userInDto));
        }

}
