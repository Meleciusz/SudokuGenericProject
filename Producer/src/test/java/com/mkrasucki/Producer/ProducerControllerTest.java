package com.mkrasucki.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import Sender.Sender;

@WebMvcTest(ProducerController.class)
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Sender sender;

//    @Test
//    public void testSendEndpoint() throws Exception {
//        String message = "Test message";
//
//        // Mock configuration
//        Mockito.doNothing().when(sender).send(message);
//
//        // Controller call
//        mockMvc.perform(MockMvcRequestBuilders.get("/send")
//                        .param("message", message))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//        // Check if send() method is called
//        Mockito.verify(sender, Mockito.times(1)).send(message);
//    }
}