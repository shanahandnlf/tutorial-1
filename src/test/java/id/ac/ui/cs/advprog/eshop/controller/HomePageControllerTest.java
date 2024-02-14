package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class HomePageControllerTest {

    @Test
    void homePage() {
        HomePageController homePageController = new HomePageController();
        Model model = mock(Model.class);

        String viewName = homePageController.homePage();

        assertEquals("homePage", viewName);
    }
}
