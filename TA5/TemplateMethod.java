package DesignPatterns;

 // Abstract class defining the template method
    abstract class BeverageTemplate {

        // Template method
        public final void prepareBeverage() {
            boilWater();
            brew();
            pourInCup();
            addCondiments();
        }

        // Abstract methods to be implemented by subclasses
        protected abstract void boilWater();

        protected abstract void brew();

        protected abstract void pourInCup();

        protected abstract void addCondiments();
    }

    // Concrete class for preparing Coffee
    class Coffee extends BeverageTemplate {

        @Override
        protected void boilWater() {
            System.out.println("Boiling water for coffee");
        }

        @Override
        protected void brew() {
            System.out.println("Brewing coffee grounds");
        }

        @Override
        protected void pourInCup() {
            System.out.println("Pouring coffee into cup");
        }

        @Override
        protected void addCondiments() {
            System.out.println("Adding sugar and milk to coffee");
        }
    }

    // Concrete class for preparing Tea
    class Tea extends BeverageTemplate {

        @Override
        protected void boilWater() {
            System.out.println("Boiling water for tea");
        }

        @Override
        protected void brew() {
            System.out.println("Steeping the tea");
        }

        @Override
        protected void pourInCup() {
            System.out.println("Pouring tea into cup");
        }

        @Override
        protected void addCondiments() {
            System.out.println("Adding lemon to tea");
        }
    }

    // Concrete class for preparing FreshTea without condiments
    class FreshTea extends BeverageTemplate {

        @Override
        protected void boilWater() {
            System.out.println("Boiling water for fresh tea");
        }

        @Override
        protected void brew() {
            System.out.println("Steeping the fresh tea leaves");
        }

        @Override
        protected void pourInCup() {
            System.out.println("Pouring fresh tea into cup");
        }

        @Override
        protected void addCondiments() {
            // No condiments for fresh tea
        }
    }

    // Concrete class for preparing Espresso without sugar and milk
    class Espresso extends BeverageTemplate {

        @Override
        protected void boilWater() {
            System.out.println("Boiling water for espresso");
        }

        @Override
        protected void brew() {
            System.out.println("Brewing espresso grounds");
        }

        @Override
        protected void pourInCup() {
            System.out.println("Pouring espresso into cup");
        }

        @Override
        protected void addCondiments() {
            // No sugar and milk for espresso
        }
    }

    // Client code using the Template Method pattern
    public class TemplateMethod {

        public static void main(String[] args) {
            BeverageTemplate coffee = new Coffee();
            System.out.println("Preparing Coffee:");
            coffee.prepareBeverage();

            System.out.println("\n");

            BeverageTemplate tea = new Tea();
            System.out.println("Preparing Tea:");
            tea.prepareBeverage();

            System.out.println("\n");

            BeverageTemplate freshTea = new FreshTea();
            System.out.println("Preparing Fresh Tea:");
            freshTea.prepareBeverage();

            System.out.println("\n");

            BeverageTemplate espresso = new Espresso();
            System.out.println("Preparing Espresso:");
            espresso.prepareBeverage();
        }


}
