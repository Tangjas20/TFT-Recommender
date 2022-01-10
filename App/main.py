import tkinter as tk
import os
import sys

    
class Window(tk.Frame):                           # <-- avoid star imports

    def __init__(self, master=None):
        self.master = master
        super().__init__(master, bg='LightGray')  # <-- use super instead of hardcoding the parent class

        self.init_window()

    def init_window(self):
        self.master.title("GUI")
        self.master.geometry('800x400')
        self.pack(fill=tk.BOTH, expand=True)

        self.quit_button = tk.Button(self, text='Quit', command=self.master.destroy)
        self.quit_button.pack(side = tk.BOTTOM, pady = 20)
        self.screenshot_button = tk.Button(self, text="Take screenshot")
       # self.screenshot_button.grid(column=4)
      #  self.screenshot_button.place(x = 50, y = 200)
        self.screenshot_button.pack(padx = 50, pady=20)
      #  self.screenshot_button.pack()

def take_screenshots():
    pass
    

if __name__ == '__main__':
    root = tk.Tk()
    app = Window(root)
    root.mainloop()