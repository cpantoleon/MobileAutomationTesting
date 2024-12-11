package project.test.swag.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.test.swag.actions.AppUI;

public class loginStepDef extends configStepDef{

    @When("I enter the username {string}")
    public void iEnterTheUsername(String user){
        loginActions.insertTheUsernameInLoginPage(user, driver);
    }

    @When("I enter the password {string}")
    public void iEnterThePassword(String user) {
        loginActions.insertThePasswordInLoginPage(user, driver);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginActions.clickOnLoginButton(driver);
    }

    @Then("An error message is displayed")
    public void anErrorMessageIsDisplayed() {
        loginQuestions.anErrorMessageIsDisplayed(driver);
    }

    @Then("The user logged in successfully")
    public void theUserLoggedInSuccessfully() {
        loginQuestions.userLoggedInSuccessfully(driver);
    }

    @Then("the user clicks to logout button")
    public void theUserClicksToLogoutButton() {
        appUI.userClicksOnNavigatorMenu(driver);
        appUI.userClicksOnLogoutOption(driver);
    }

    @And("the user logged out successfully")
    public void theUserLoggedOutSuccessfully() {
        loginQuestions.userLoggedOutSuccessfully(driver);
    }
}

