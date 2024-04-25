Coding Challenges - Build your own WC tool

commands:
- ccwc -c {fileName.txt} - counts bytes in a file
- ccwc -l {fileName.txt} - counts lines in a file
- ccwc -w {fileName.txt} - counts words in a file
- ccwc -w {fileName.txt} - counts characters in a file
- cat {fileName.txt} | ccwc {command}

In below visual example, I used WinPShell(admin) because I did not want to bother with configuring restrictions. Which is why below syntax is cat {fileName.txt} | .\\{.exe/.bat name} {command}
![WCtoolCommands](https://github.com/hirayui1/javaCCWC/assets/140653451/f2a4c91d-d9a4-4b34-bcc0-b9abba9a672e)
