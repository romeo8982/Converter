package com.example.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ConverterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkRomeTypeArabicNumberNotEmpty() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "123");
        requestParams.add("radioType", "romeType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("CXXIII")));
    }

    @Test
    public void checkRomeTypeArabicNumberEmpty() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "");
        requestParams.add("radioType", "romeType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie podano liczby do konwersji.")));
    }

    @Test
    public void checkRomeTypeArabicNegativeNumber() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "-123");
        requestParams.add("radioType", "romeType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Jeśli chcesz skorzstać z konwertera liczb rzyskich wprowadź dodatnią wartość.")));
    }

    @Test
    public void checkRomeTypeArabicTextString() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "abc");
        requestParams.add("radioType", "romeType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.")));
    }

    @Test
    public void checkRomeTypeArabicFloatNumber() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "12.3");
        requestParams.add("radioType", "romeType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.")));
    }

    @Test
    public void checkRomeTypeArabicOverRangeNumber() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "7777777777777777");
        requestParams.add("radioType", "romeType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.")));
    }

    @Test
    public void checkHexTypeArabicNumberNotEmpty() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "123");
        requestParams.add("radioType", "hexType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("7b")));
    }

    @Test
    public void checkHexTypeArabicNumberEmpty() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "");
        requestParams.add("radioType", "hexType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie podano liczby do konwersji.")));
    }

    @Test
    public void checkHexTypeArabicNegativeNumber() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "-123");
        requestParams.add("radioType", "hexType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("ffffff85")));
    }

    @Test
    public void checkHexTypeArabicTextString() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "abc");
        requestParams.add("radioType", "hexType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.")));
    }

    @Test
    public void checkHexTypeArabicFloatNumber() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "12.3");
        requestParams.add("radioType", "hexType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.")));
    }

    @Test
    public void checkHexTypeArabicOverRangeNumber() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "7777777777777777");
        requestParams.add("radioType", "hexType");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono liczby całkowitej z przedziału <-2^31, 2^31-1>.")));
    }

    @Test
    public void checkRadioUnknownType() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("arabicNumber", "-123");
        requestParams.add("radioType", "qwerty");
        this.mockMvc.perform(post("/convert").params(requestParams)).andExpect(content().string(containsString("Nie wprowadzono poprawnego typu konwersji.")));
    }
}