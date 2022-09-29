package com.bob.learn.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源
 *
 * @author Bob
 * @date 2022/7/18 16:17
 */
public interface Resource {

    /**
     * 输入流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
