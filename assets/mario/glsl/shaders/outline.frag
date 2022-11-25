#version 120

uniform sampler2D texture;
uniform vec2 resolution;

// Colour and width
uniform vec4 colour;
uniform float width;

uniform int fill;
uniform int outline;

void main() {
    vec4 centerCol = texture2D(texture, gl_TexCoord[0].xy);

    if (centerCol.a > 0) {
        if (fill == 1) {
            gl_FragColor = vec4(colour.x, colour.y, colour.z, 0.5F);
        } else {
            gl_FragColor = vec4(0, 0, 0, 0);
        }
    } else if (outline == 1) {
        float alpha = 0.0F;

        for (float x = -width; x <= width; x++) {
            for (float y = -width; y <= width; y++) {
                vec4 pointColour = texture2D(texture, gl_TexCoord[0].xy + vec2(resolution.x * x, resolution.y * y));

                if (pointColour.a > 0) {
                    alpha = 1.0F;
                }
            }
        }

        gl_FragColor = vec4(colour.x, colour.y, colour.z, alpha);
    }
}
