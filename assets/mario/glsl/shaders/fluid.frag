#version 120

uniform sampler2D texture;

uniform float time;

void main() {
    vec4 centerCol = texture2D(texture, gl_TexCoord[0].xy);

    if (centerCol.a > 0) {
        vec2 uv = vec2(gl_TexCoord[0].x * 5.0, gl_TexCoord[0].y * 10.0);

        for (float i = 1.5; i < 15.0; i += 0.75) {
            uv.x += sin((uv.y + (time / 3.0) * -i) * (i / 2.0)) / i + time / 20.0 * 1.25;
            uv.y += i * 0.35 / 5.0 + uv.x * 2.0 / i * -1.5 + (time * 0.01);
        }

        float r = sin(uv.x / 3.2);
        float g = sin(uv.x / 2.0);
        float b = sin(uv.x / 1.35);

        gl_FragColor = vec4(r, g, b, 1.0);
    } else {
        gl_FragColor = vec4(0.0, 0.0, 0.0, 0.0);
    }
}
