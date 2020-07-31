#test = open("mixmilk.in", "w")
#test.write("10 3\n11 4\n12 5")
#test.close()

inFile = open("mixmilk.in", "r")
outFile = open("mixmilk.out", "w")

cap = [0]*3
con = [0]*3

for i in range(3):
    line = inFile.readline().split()
    cap[i] = int(line[0])
    con[i] = int(line[1])

curr = 0
nex = 1

for i in range(100):
    #pour curr into nex
    amount = min(con[curr],cap[nex]-con[nex])
    con[curr] -= amount
    con[nex] += amount
    nex = (nex + 1) % 3
    curr = (curr + 1) % 3

outFile.write(str(con[0]) + "\n" + str(con[1]) + "\n" + str(con[2]))
inFile.close()
outFile.close()
