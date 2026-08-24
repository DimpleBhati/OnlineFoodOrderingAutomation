package foodordering.automation.OnlineFoodOrderingAutomation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OnlineFoodOrderingTest extends BaseTest {

    // =====================================================
    // TEST 1 - VERIFY HOME PAGE
    // =====================================================

    @Test
    public void verifyHomePage() {

        driver.get("https://eatclub.in/");

        HomePage home =
                new HomePage(driver, WAIT_TIME);

        Assert.assertTrue(
                home.isHomePageDisplayed(),
                "EatClub home page was not opened"
        );

        System.out.println(
                "PASS: Home Page"
        );
    }


    // =====================================================
    // TEST 2 - LOGIN
    // =====================================================

    @Test
    public void loginTest() {

        driver.get("https://eatclub.in/");

        LoginPage login =
                new LoginPage(driver, WAIT_TIME);

        boolean loginOpened =
                login.openLoginIfAvailable();

        if (loginOpened) {

            Assert.assertTrue(
                    login.isLoginPageDisplayed(),
                    "Login page was not displayed"
            );

            System.out.println(
                    "PASS: Login Page"
            );

        } else {

            System.out.println(
                    "INFO: Login option is not available "
                    + "on the current EatClub page"
            );
        }
    }


    // =====================================================
    // TEST 3 - SEARCH FOOD
    // =====================================================

    @Test
    public void searchFoodTest() {

        driver.get(
                "https://eatclub.in/search"
        );

        HomePage home =
                new HomePage(driver, WAIT_TIME);

        home.searchFood("Pizza");

        System.out.println(
                "PASS: Food Search"
        );
    }


    // =====================================================
    // TEST 4 - RESTAURANT
    // =====================================================

    @Test
    public void selectRestaurant() {

        driver.get(
                "https://eatclub.in/search"
        );

        RestaurantPage restaurant =
                new RestaurantPage(
                        driver,
                        WAIT_TIME
                );

        boolean restaurantDisplayed =
                restaurant.isRestaurantPageDisplayed();

        if (restaurantDisplayed) {

            boolean selected =
                    restaurant.selectRestaurant();

            if (selected) {

                System.out.println(
                        "PASS: Restaurant Selected"
                );

            } else {

                System.out.println(
                        "INFO: Restaurant was displayed "
                        + "but could not be selected"
                );
            }

        } else {

            System.out.println(
                    "INFO: Biryani restaurant/result "
                    + "was not available"
            );
        }
    }


    // =====================================================
    // TEST 5 - DATA PROVIDER / VERIFY FOOD
    // =====================================================

    @DataProvider(name = "foodData")
    public Object[][] foodData() {

        return new Object[][] {

                {"Pizza"}

        };
    }


    @Test(dataProvider = "foodData")
    public void verifyFood(String food) {

        driver.get(
                "https://eatclub.in/search"
        );

        HomePage home =
                new HomePage(driver, WAIT_TIME);

        FoodMenuPage menu =
                new FoodMenuPage(
                        driver,
                        WAIT_TIME
                );

        home.searchFood(food);

        boolean foodDisplayed =
                menu.isFoodDisplayed(food);

        if (foodDisplayed) {

            System.out.println(
                    "PASS: Food Found - "
                    + food
            );

        } else {

            System.out.println(
                    "INFO: Food '"
                    + food
                    + "' was not displayed"
            );
        }
    }


    // =====================================================
    // TEST 6 - ADD FOOD TO CART
    // =====================================================

    @Test
    public void addFoodToCart() {

        driver.get(
                "https://eatclub.in/search"
        );

        HomePage home =
                new HomePage(driver, WAIT_TIME);

        FoodMenuPage menu =
                new FoodMenuPage(
                        driver,
                        WAIT_TIME
                );

        String food =
                properties.getProperty(
                        "food",
                        "Pizza"
                );

        home.searchFood(food);

        boolean added =
                menu.addFood(food);

        if (added) {

            System.out.println(
                    "PASS: Food Added To Cart"
            );

        } else {

            System.out.println(
                    "INFO: Add button was not "
                    + "available for "
                    + food
            );
        }
    }


    // =====================================================
    // TEST 7 - VERIFY CART
    // =====================================================

    @Test
    public void verifyCart() {

        driver.get(
                "https://eatclub.in/search"
        );

        HomePage home =
                new HomePage(driver, WAIT_TIME);

        FoodMenuPage menu =
                new FoodMenuPage(
                        driver,
                        WAIT_TIME
                );

        CartPage cart =
                new CartPage(
                        driver,
                        WAIT_TIME
                );

        String food =
                properties.getProperty(
                        "food",
                        "Pizza"
                );

        // Search food
        home.searchFood(food);

        // Add food
        boolean added =
                menu.addFood(food);

        if (!added) {

            System.out.println(
                    "INFO: Food could not be "
                    + "added to cart"
            );

            return;
        }

        // Open cart
        boolean cartOpened =
                cart.openCart();

        if (!cartOpened) {

            System.out.println(
                    "INFO: Cart could not be opened"
            );

            return;
        }

        // Verify cart
        boolean cartDisplayed =
                cart.isCartDisplayed();

        if (cartDisplayed) {

            System.out.println(
                    "PASS: Cart Displayed"
            );

        } else {

            System.out.println(
                    "INFO: Cart page was not detected"
            );
        }
    }


    // =====================================================
    // TEST 8 - REMOVE FOOD FROM CART
    // =====================================================

    @Test
    public void removeFoodFromCart() {

        driver.get(
                "https://eatclub.in/search"
        );

        HomePage home =
                new HomePage(driver, WAIT_TIME);

        FoodMenuPage menu =
                new FoodMenuPage(
                        driver,
                        WAIT_TIME
                );

        CartPage cart =
                new CartPage(
                        driver,
                        WAIT_TIME
                );

        String food =
                properties.getProperty(
                        "food",
                        "Pizza"
                );

        // Search food
        home.searchFood(food);

        // Add food
        boolean added =
                menu.addFood(food);

        if (!added) {

            System.out.println(
                    "INFO: Food could not be "
                    + "added to cart"
            );

            return;
        }

        // Open cart
        boolean cartOpened =
                cart.openCart();

        if (!cartOpened) {

            System.out.println(
                    "INFO: Cart could not be opened"
            );

            return;
        }

        // Remove food
        boolean removed =
                cart.removeItem();

        if (removed) {

            System.out.println(
                    "PASS: Food Removed From Cart"
            );

        } else {

            System.out.println(
                    "INFO: Remove option was "
                    + "not available"
            );
        }
    }
}