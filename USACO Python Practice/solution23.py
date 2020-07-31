#test = open("homework.in", "w")
#test.write("5\n3 1 9 2 7")
#test.close()

inFile = open("homework.in", "r")
outFile = open("homework.out", "w")

num = int(inFile.readline()) # number of homework assignments
scores = inFile.readline().split() # list of scores as strings
queue = [] # queue of answers in reverse order
maxScore = 0 # the maximum score thus far
total = lowest = int(scores[-1]) # grand total and min element resp.

for i in range(num - 2, -1, -1):
	lowest = min(lowest , int(scores[i]))
	total += int(scores[i])
	current = (total - lowest)/(num - 1 - i)
	if current == maxScore:
		queue.append(i)
	elif current > maxScore and current != num - 2:
		queue.clear()
		queue.append(i)
		maxScore = current

ans = str(queue[0])
for i in range(len(queue)-1):
	ans = str(queue[i]) + "\n" + ans  

outFile.write(ans)
inFile.close()
outFile.close()

'''
start from the end and move forward

Queue answers empty
maxScore = 0
minElement = last element
total = last element




move forward in a for loop:
	each time we update lowest
	we update total
	we calculate what the score on homework is
	update queue if it exceeds max


'''