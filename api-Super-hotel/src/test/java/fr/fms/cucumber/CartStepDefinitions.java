package fr.fms.cucumber;

import fr.fms.business.IBusiness;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.jfr.Category;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;



public class CartStepDefinitions {

    @Autowired
    MockMvc mockMvc;

    ResultActions response;

    @Autowired
    private IBusiness business;

    @Given("add City")
    public void la_page_contient_des_viles() {
        City city = new City(1L,"Paris",null);
        business.addCity(city);
    }

    @When("when")
    public void fonction() throws Exception {
        response = mockMvc.perform(get("/cities"));
    }

    @Then("then")
    public void fonctionThen() throws Exception {
        //response.andExpect(jsonPath("$[*].name",hasItems("Paris")));
        //response.andExpect(jsonPath("$[?(@.name == 'Paris')]").exists());
        assertThat(response.andReturn().getResponse().getContentAsString(), containsString("Paris"));
    }


//    @When("l'utilisateur accède à la page vcart")
//    public void l_utilisateur_accede_a_la_page_vcart() throws Exception {
//        response = mockMvn.perform(get("/vcart"));
//    }
//
//    @Then("la page est renvoyée avec un status {int}")
//    public void la_page_est_renvoyee_avec_un_statut(Integer int1) throws Exception{
//        response.andExpect(status().isOk());
//    }
}
