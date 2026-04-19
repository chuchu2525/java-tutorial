package com.example.demo.library.web;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.library.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryPageExceptionHandlerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LibraryService libraryService;

    @Test
    void getLibraryWhenServiceThrows_redirectsWithFlashMessage() throws Exception {
        when(libraryService.getBooks()).thenThrow(new IllegalStateException("test failure"));

        mockMvc.perform(get("/library"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/library"))
                .andExpect(flash().attribute("messageType", "error"))
                .andExpect(flash().attribute("message", containsString("予期しない")));
    }
}
