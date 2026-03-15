package com.seveneleven.mycontactapp.contact.display;

public class FancyDisplayDecorator extends ContactDisplayDecorator {

    public FancyDisplayDecorator(ContactDisplay display) {
        super(display);
    }

    @Override
    public String display() {

        return "\n=========================\n"
                + display.display()
                + "\n=========================\n";
    }
}