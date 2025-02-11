package com.methods;

import com.objects.CarObject;
import com.objects.CarList;
import com.global.Helpers;

public class Add extends com.global.BasicInputs {

    CarObject myCarObject = new CarObject();

    public Add() {
        clrscr();

        print("Add new Car.");
        print("");

        /**
         * Check if we have available slots
         */
        if (CarList.length() >= CarList.carList.length) {
            print("No Available Slots");
            print("Delete some records to continue");

            print("Press enter to continue...");
            inStr();

            return;
        }

        print("Please, fill up the form.");

        /**
         * Get car specs
         */
        this.get_brand();
        this.get_model();
        this.get_type();
        this.get_color();
        this.get_torque();
        this.get_engine();
        this.get_price();

        this.myCarObject.id = generate_id();

        print("\n");
        print("This car with this specification will be added to your list.");
        print("");
        print(String.format("Brand: %s", this.myCarObject.brand));
        print(String.format("Model: %s", this.myCarObject.model));
        print(String.format("Type: %s", this.myCarObject.type));
        print(String.format("Color: %s", this.myCarObject.color));
        print(String.format("Torque: %s", this.myCarObject.torque));
        print(String.format("Engine: %s", this.myCarObject.engine));
        print(String.format("Price: %.2f", this.myCarObject.price));
        print(String.format("Generated ID: %s", this.myCarObject.id));
        print("");

        /**
         * Confirmation to add this car into CarObject array
         */
        if (Confirm("Are you sure to add this car to your list?")) {

            try {
                CarList.add_item(this.myCarObject);

                print("");
                print("Car has been added.");

            } catch (Exception e) {
                print("Failed to add");
                print("Please, freeup some space to continue");

            } finally {
                print("");
                print("Press enter to continue...");
                inStr();
            }
        }

        clrscr();
    }

    /**
     * Generate Interger ID for CarObject
     * 
     * @return int
     */
    public static int generate_id() {
        int len = 4; // Length of an ID
        String sb; // String Builder
        int sample_id; // Parsed ID

        int index, rand;

        while (true) {
            sb = ""; // Reset

            // Create @len integer ID
            for (index = 0; index < len; index++) {
                rand = (int) Math.round(Math.random() * 9);
                sb = String.format("%s%d", sb, rand);
            }

            try {
                // Parse it and check if ID does exists
                sample_id = Integer.parseInt(sb);

                if (CarList.has(sample_id)) {
                    // ID does exists... Regenerate
                    continue;
                }

                return sample_id;
            } catch (Exception e) {
            }
        }
    }

    /**
     * Get the price of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_price() {
        float num;

        do {
            print("");
            print("Price of the car (Php): ");
            num = inFloat();
        } while (num < 0.2);

        this.myCarObject.price = num;
    }

    /**
     * Get engine type of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_engine() {
        this.myCarObject.engine = this.get_validated_input("Engine of a car: ");
    }

    /**
     * Get torque power of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_torque() {
        this.myCarObject.torque = this.get_validated_input("Torque of a car: ");
    }

    /**
     * Get the color of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_color() {
        String res = this.get_validated_input("Color of a car: ");
        this.myCarObject.color = Helpers.ucfirst(res);
    }

    /**
     * Get the body type of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_type() {
        String res = this.get_validated_input("Type of a car: ");
        this.myCarObject.type = Helpers.ucfirst(res);
    }

    /**
     * Get the brand of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_brand() {
        String res = this.get_validated_input("Brand of a car: ");
        this.myCarObject.brand = Helpers.ucfirst(res);
    }

    /**
     * Get the model of a car then assign it to new CarObject
     * 
     * @return void
     */
    public void get_model() {
        this.myCarObject.model = this.get_validated_input("Model of a car: ");
    }

    /**
     * Get the validated string
     *   
     * @return string
     */
    private String get_validated_input(String placeholder) {
        /**
         * Validate inputs
         */

        String str;

        do {
            print("");
            print(placeholder);

            str = inStr();

            if (str == null) {
                System.exit(0);
            }

            str = str.trim();
        } while (str.isEmpty());

        return str;
    }
}
