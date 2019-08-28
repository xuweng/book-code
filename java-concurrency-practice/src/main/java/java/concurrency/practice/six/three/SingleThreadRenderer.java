package java.concurrency.practice.six.three;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Rendering page elements sequentially.
 */
public class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<ImageData>();
        for (ImageInfo imageInfo : scanForImageInfo(source))
            imageData.add(imageInfo.downloadImage());
        for (ImageData data : imageData)
            renderImage(data);
    }

    private void renderImage(ImageData data) {
    }

    private ImageInfo[] scanForImageInfo(CharSequence source) {
    }

    private void renderText(CharSequence source) {
    }
}
