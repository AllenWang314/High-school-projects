#test = open("traffic.in", "w")
#test.write("4\non 1 1\nnone 10 14\nnone 11 15\noff 2 3")
#test.close()

inFile = open("traffic.in", "r")
outFile = open("traffic.out", "w")

num = int(inFile.readline())

lines = []
for i in range(num):
    lines.append(inFile.readline().split())

ramps = []
ramps2 = []
high = []

for i in range(num):
    if lines[i][0] == "on":
        high.append([0,1000])
        ramps.append([min(int(lines[i][1]), int(lines[i][2])),max(int(lines[i][1]), int(lines[i][2]))])
        ramps2.append([min(-int(lines[i][1]), -int(lines[i][2])),max(-int(lines[i][1]), -int(lines[i][2]))])
    elif lines[i][0] == "off":
        high.append([0,1000])
        ramps.append([min(-int(lines[i][1]), -int(lines[i][2])),max(-int(lines[i][1]), -int(lines[i][2]))])
        ramps2.append([min(int(lines[i][1]), int(lines[i][2])),max(int(lines[i][1]), int(lines[i][2]))])
    else:
        ramps.append([0,0])
        ramps2.append([0,0])
        high.append([min(int(lines[i][1]), int(lines[i][2])),max(int(lines[i][1]), int(lines[i][2]))])
#
#print(ramps)
#print(ramps2)
#print(high)

ans1 = [0,1000]
for i in range(num):
    ans1[0] += ramps[i][0]
    ans1[1] += ramps[i][1]
    ans1[0] = max(ans1[0],high[i][0])
    ans1[1] = min(ans1[1],high[i][1])

ans2 = [0,1000]
for i in range(num-1,-1,-1):
    #print(ans2)
    ans2[0] += ramps2[i][0]
    ans2[1] += ramps2[i][1]
    ans2[0] = max(ans2[0],high[i][0])
    ans2[1] = min(ans2[1],high[i][1])

outFile.write(str(ans2[0]) + " " + str(ans2[1]) + "\n" + str(ans1[0]) + " " + str(ans1[1]))

inFile.close()
outFile.close()
