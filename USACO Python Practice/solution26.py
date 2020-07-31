'''
test = open("shuffle.in", "w")
test.write("8\n1 4 1 5 4 5 1 8")
test.close()
'''
import queue

inFile = open("shuffle.in", "r")
outFile = open("shuffle.out", "w")
num = int(inFile.readline())
shuff = inFile.readline().split()
for i in range(num):
	shuff[i] = int(shuff[i]) - 1
dead = set([])
count = [0]*num
L = queue.Queue(maxsize=1000000)

for i in range(num):
	count[shuff[i]] += 1 

for i in range(num):
	if count[i] == 0:
		L.put(i)

while not L.empty():
	element = L.get()
	count[shuff[element]] -= 1
	if count[shuff[element]] == 0:
		L.put(shuff[element])

answer = 0

for element in count:
	if element >0:
		answer += 1

outFile.write(str(answer))
inFile.close()
outFile.close()