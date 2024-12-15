package project.test.swag.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.test.swag.utils.YMLConfig;

public class StoreStepDef extends configStepDef{

    @When("Add a product to the basket")
    public void addAProcuctToTheBasket(){
        appUI.selectAProduct(driver);
        appUI.addTheProcutToTheBasket(driver);
    }

    @And("Click on the Cart icon")
    public void clickOnTheCartIcon() {
        appUI.clickOnTheCartIcon(driver);
    }

    @Then("Continue the flow until the order to complete")
    public void continueTheFlowUntilTheOrderToComplete() {
        appUI.clickOnTheCheckoutButton(driver);
        appUI.fillTheMandatoryInformationFields(driver,
                YMLConfig.firstNameValues.get("firstName1"),
                YMLConfig.lastNameValues.get("lastName1"),
                YMLConfig.zipCodeValues.get("zipCode1"));
        appUI.clickOnTheContinueButton(driver);
        appUI.clickOnTheFinishButton(driver);
    }

    @And("The user complete the order successfully")
    public void theUserCompleteTheOrderSuccessfully() {
        appQuestions.VerifyTheOrderSentSuccessuflly(driver);
    }
}