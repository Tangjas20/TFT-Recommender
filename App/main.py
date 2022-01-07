import tkinter as tk


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
        self.quit_button.pack()


if __name__ == '__main__':

    root = tk.Tk()
    app = Window(root)
    root.mainloop()