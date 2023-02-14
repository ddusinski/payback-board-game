package com.dusinski;

import com.dusinski.service.Checkerboard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaybackBoardGame {
    private static final Logger logger = LogManager.getLogger(PaybackBoardGame.class);

    public static void main(String[] args) {
        logger.info("Hello PaybackBoardGame game!");
        Checkerboard checkerboard = new Checkerboard();
        logger.info(checkerboard.printCheckerboard());
        System.out.println(checkerboard.printCheckerboard());
        for (int i = 1; i <= 100; i++) {
            checkerboard.flyTheBird();
            if (i == 25 || i == 50 || i == 100) {
                logger.info("Round: " + i);
                logger.info(checkerboard.printCheckerboard());
            }
        }
        logger.info("end of the program");
    }
}
