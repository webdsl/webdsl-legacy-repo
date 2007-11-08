package webdsl.safari.outliner;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.imp.services.IOutlineImage;

public class WebDSLImages implements IOutlineImage {
	private WebDSLImages() {
	}

	private static IOutlineImage image = null;

	public static IOutlineImage getWebDSLImages() {
		if (image == null) {
			image = new WebDSLImages();
		}
		return image;
	}

	public static final String IMAGE_ROOT = "icons";

	public static ImageDescriptor OUTLINE_ITEM_DESC = AbstractUIPlugin
			.imageDescriptorFromPlugin("WebDSL", IMAGE_ROOT
					+ "/outline_item.gif");

	public static Image OUTLINE_ITEM_IMAGE = OUTLINE_ITEM_DESC.createImage();

	public String getImageRoot() {
		return IMAGE_ROOT;
	}

	public ImageDescriptor getOutlineItemDesc() {
		return OUTLINE_ITEM_DESC;
	}

	public Image getOutlineItemImage() {
		return OUTLINE_ITEM_IMAGE;
	}

}
