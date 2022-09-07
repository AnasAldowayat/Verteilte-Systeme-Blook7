package de.umr.ds.task1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {

	/**
	 * Loads an image from the given file path
	 *
	 * @param path The path to load the image from
	 * @return BufferedImage
	 */
	private static BufferedImage loadImage(String path) throws IOException {
		// TODO Task 1a)
		return ImageIO.read(new File("C:\\tmp\\ProPra22-Aufgaben\\example.jpg"));
	}

	/**
	 * Converts the input RGB image to a single-channel gray scale array.
	 * 
	 * @param img The input RGB image
	 * @return A 2-D array with intensities
	 */
	private static int[][] convertToGrayScaleArray(BufferedImage img) {
		// TODO Task 1b)
		int[][] grauImg = new int[img.getWidth()][img.getHeight()];
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int rgb = img.getRGB(i,j);
				Color color = new Color(rgb);
				grauImg[i][j] =(int)( 0.299*color.getRed() +0.587*color.getGreen() + 0.114 * color.getBlue());
			}
		}
		return grauImg;
	}


	/**
	 * Converts a single-channel (gray scale) array to an RGB image.
	 * 
	 * @param img The input image array
	 * @return BufferedImage
	 */
	private static BufferedImage convertToBufferedImage(int[][] img) {
		// TODO Task 1c)
		int imgWidth = img.length;
		int imgHeight = img[0].length;
		BufferedImage f =new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		for(int i = 0 ; i < imgWidth ; i++){
			for(int j = 0 ; j < imgHeight; j++){
				int rgbValue = img[i][j];
				Color color = new Color(rgbValue,rgbValue,rgbValue);
				int c =color.getRGB();
				f.setRGB(i, j, c);
			}
		}
		return f;
	}

	/**
	 * Saves an image to the given file path
	 *
	 * @param img The RGB image
	 * @param path The path to save the image to
	 */
	private static void saveImage(BufferedImage img, String path) throws IOException {
		// TODO Task 1c)
		File f = new File(path);
		ImageIO.write(img,"jpg",f);
	}

	/**
	 * Converts input image to gray scale and applies the kernel.
	 * 
	 * @param img The RGB input image
	 * @param kernel The kernel to apply
	 * @return The convolved gray-scale image
	 */
	private static BufferedImage filter(BufferedImage img, Kernel kernel) throws IOException {
		// TODO Task 1f)
		int[][] alteIMG =convertToGrayScaleArray(img);
		int[][] newImg = kernel.convolve(alteIMG);

		return resizeImage( convertToBufferedImage(newImg), img.getWidth(), img.getHeight());
	}


	// TODO Task 1g)
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}


	public static void main(String[] args) throws IOException {
		// TODO
		BufferedImage img = loadImage("C:\\tmp\\ProPra22-Aufgaben\\example.jpg");
		int[][] inT =convertToGrayScaleArray(img);
		BufferedImage newImg =convertToBufferedImage(inT);
		saveImage(newImg, "C:\\tmp\\ProPra22-Aufgaben\\newExample.jpg");



		 Kernel GaussianBlur = Kernels.GaussianBlur5x5();
		 BufferedImage GaussianBlurImg = filter(newImg,GaussianBlur);
		 saveImage(GaussianBlurImg,"C:\\tmp\\ProPra22-Aufgaben\\GaussianBlurImg.jpg");

		Kernel BoxBlur = Kernels.BoxBlur3x3();
		BufferedImage BoxBlurImg = filter(newImg,BoxBlur);
		saveImage(BoxBlurImg,"C:\\tmp\\ProPra22-Aufgaben\\BoxBlur3x3Img.jpg");

	/*	Kernel EdgeDetection1 = Kernels.EdgeDetection();
		BufferedImage EdgeDetectionImg = filter(newImg,EdgeDetection1);
		saveImage(EdgeDetectionImg,"C:\\tmp\\ProPra22-Aufgaben\\EdgeDetectionImg.jpg");

	 */

	    Kernel anas = Kernels.Anas();
		BufferedImage anasImg = filter(newImg,anas);
		saveImage(anasImg,"C:\\tmp\\ProPra22-Aufgaben\\anasImg.jpg");





	}



}
