#version 120

uniform sampler2D texture;
uniform vec2 resolution;

uniform float time;
uniform vec4 colour;

void main() {
    vec4 center = texture2D(texture, gl_TexCoord[0].xy);

    if (center.a > 0.0) {
        float pi = 3.141592;
        vec2 uv = gl_FragCoord.xy * 2.0 / 1000.0;

        float col = cos((uv.y * uv.x + time * 1.5 + cos(2.0 * uv.y + 3.0 * uv.x) * pi * 0.5) * pi * 0.8);

        vec4 finalColour = colour;

        finalColour += col * 0.3;

        gl_FragColor = finalColour;
    } else {
        gl_FragColor = vec4(0.0, 0.0, 0.0, 0.0);
    }
}
