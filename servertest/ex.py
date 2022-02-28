import cv2
import numpy as np

img = cv2.imread("bb.jpg")

cv2.putText(img, "한글", (100, 100), cv2.FONT_HERSHEY_PLAIN, 3, (0, 0, 0), 3)

cv2.imshow("dd", img)
cv2.waitKey(0)
cv2.destroyAllWindows()