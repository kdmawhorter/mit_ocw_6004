The intent of this project is to provide myself an opportunity to follow 
along with the contents of 
<a href="https://ocw.mit.edu/courses/6-004-computation-structures-spring-2017/">
MIT OCW 6.004</a>, which was using some type of circuit builder to do their 
exercises. Since I didn't have access to that, and since building a rudimentary
one would be good practice, I did it here.

The fundamental abstractions used as the base architecture consist of 
the following:
<ul>
<li>A <b>CircuitNode</b> has a status, either connected to power, ground, both, or 
neither</li>
<li>A <b>CircuitNode</b> can be connected to an arbitrary number of 
<b>DigitalCircuits</b></li>
<li>A <b>Port</b> is a means of breaking up a <b>CircuitNode</b> into an input side and
output side to facilitate connecting components (effectively cleaving a 
single node to better enforce the <b>DigitalCircuit</b> blackbox)</li>
<li>A <b>DigitalCircuit</b> is a device that has some inputs and outputs, connected
through <b>Ports</b></li>
<li>A <b>DigitalCircuit</b> can evaluate its internal components to generate an
output given its inputs, by reading its input <b>Ports</b>, evaluating its circuity,
and then pushing to its output <b>Ports</b></li>
<li>A <b>DigitalCircuit</b> can impart some stats onto the <b>CircuitNodes</b> its 
connected to (e.g. connect them to ground or power)</li>
</ul>

From that the main building blocks are very rudimentary on/off switches that 
are meant to represent MOSFETs. 

From those, the standard logic gates are built: Nand, Nor, And, Or, Xor, Inverter

From those, the standard operations are built: add, mult, div, mux, demux, shift

From those, memory modules are built for reading and writing to some contiguous array
of <b>CircuitNodes</b>.

From all that, an ALU is generated to do some very basic computing.

As of 24APR22 the next step is to create a control class move the full concept to a 
von Neumann machine.