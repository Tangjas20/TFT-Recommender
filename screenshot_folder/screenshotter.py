from PIL import ImageGrab
import os


def take_screenshot():
    same_directory = os.path.dirname(os.path.abspath(__file__))
    im = ImageGrab.grab()

    im.save(os.path.join(same_directory, 'TFT.png'))
    im.close()


take_screenshot()


