package com.solver;

import java.io.IOException;

public interface ImageHandler {

    void parseImage(String name) throws IOException;

    void drawPath();
}
