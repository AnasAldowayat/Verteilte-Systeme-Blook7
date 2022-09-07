package de.umr.ds.task1;

/**
 * A kernel is a 2D-array. The array is transposed after initialization which
 * enables a more intuitive way of initializing a kernel. E.g a non-symmetric
 * kernel can be initialized by Kernel({{0,0,1} {0,1,0} {1,0,0}}) although the
 * array dimensions are actually [height][width].
 *
 */
public class Kernel {

	private double[][] k;
	private int height;
	private int width;

	/**
	 * Initializes the kernel by its transpose.
	 * 
	 * @param k 2D array
	 */
	Kernel(double[][] k) {
		// transpose
		this.k = new double[k[0].length][k.length];
		for (int x = 0; x < k[0].length; x++)
			for (int y = 0; y < k.length; y++)
				this.k[x][y] = k[y][x];
		this.width = this.k.length;
		this.height = this.k[0].length;

		if (this.width % 2 != 1 || this.height % 2 != 1)
			throw new IllegalArgumentException("Kernel size need to be odd-numbered");
		if (this.width < 3 || this.height < 3)
			throw new IllegalArgumentException("Minimum dimension is 3");
	}

	/**
	 * Convolves a single-channel image with the kernel.
	 * 
	 * @param img A single-channel image
	 * @return The convolved image
	 */
	public int[][] convolve(int[][] img) {
		// TODO Task 1d)
		int outputWidth = img.length - width + 1;
		int outputHeight = img[0].length - height + 1;
		int[][] outputImage = new int[outputWidth][outputHeight];
		for(int x = 0; x < outputWidth;x++){
			for(int y = 0; y < outputHeight ;y++){
				for(int i = 0; i < width;i++){
					for (int j = 0; j < height; j++){
						int a = getHeight() - 1;//outputImage.length;

						outputImage[x][y] += (img[x - i + a][y - j + a] * k[i][j]);

					}
					if (outputImage[x][y]<0) outputImage[x][y]=0;
					if (outputImage[x][y]>255) outputImage[x][y]=255;

				}
			}
		}
		return outputImage;

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
