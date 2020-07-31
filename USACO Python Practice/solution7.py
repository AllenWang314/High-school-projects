#test = open("taming.in", "w")
#test.write("4\n-1 -1 -1 1")
#test.close()

#18\n0 0 1 -1 1 -1 -1 -1 -1 -1 4 -1 -1 1 -1 0 -1 0

inFile = open("taming.in", "r")
outFile = open("taming.out", "w")

d = int(inFile.readline())
line = inFile.readline().split()
log = []
logMin = []

for i in range(d):
    log.append(int(line[i]))
    logMin.append(0)
logMax = log.copy()


# maximum is computed by assumning increase then breakout every day

prev = -1
for i in range(d-1, -1,-1):
    if prev <= 0 and logMax[i] == -1:
        logMax[i] = 0
        prev = logMax[i]
    elif prev <= 0:
        prev = logMax[i]
    else:
        logMax[i] = prev-1
        prev = prev - 1

total = 0
zeroes = 0

for i in range(d):
    if(logMax[i] == 0):
        zeroes += 1
    if(logMax[i] == 0 and log[i] == -1 and i != 0 and i != d-1 and logMax[i+1]==0):
        logMin[i] = 1
if logMax[d-1] == 0 and log[d-1] == -1:
    logMin[d-1] = 1
logMin[0] = 0
for i in range(1,d):
    if (logMin[i] == 1):
        total += 1
    


#print(total)
#print(log)
#print(logMax)
#print(str(zeroes-total) + " " + str(zeroes))
outFile.write(str(zeroes-total) + " " + str(zeroes))

outFile.close()
inFile.close()
