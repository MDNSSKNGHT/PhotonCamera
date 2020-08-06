package com.eszdman.photoncamera.OpenGL.Nodes;

import com.eszdman.photoncamera.OpenGL.GLFormat;
import com.eszdman.photoncamera.OpenGL.GLInterface;
import com.eszdman.photoncamera.OpenGL.GLProg;
import com.eszdman.photoncamera.OpenGL.GLTexture;
import com.eszdman.photoncamera.OpenGL.Nodes.PostPipeline.PostPipeline;
import com.eszdman.photoncamera.Render.Parameters;

public class Debug2 extends Node {

    public Debug2(int rid, String name) {
        super(rid, name);
    }
    @Override
    public void Run() {
        PostPipeline rawPipeline = (PostPipeline)basePipeline;
        GLInterface glint = rawPipeline.glint;
        GLProg glProg = glint.glprogram;
        Parameters params = glint.parameters;
        GLTexture glTexture = new GLTexture(params.rawSize, new GLFormat(GLFormat.DataType.UNSIGNED_16),rawPipeline.glint.inputRaw);
        glProg.setTexture("InputBuffer",glTexture);
    }
}