varying lowp vec2 TextureCoordsOut;
uniform sampler2D Texture;
void main(void) {
    gl_FragColor = texture2D(Texture, TextureCoordsOut);
}
