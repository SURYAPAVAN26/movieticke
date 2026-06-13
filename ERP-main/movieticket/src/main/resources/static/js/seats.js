let selectedSeats = [];

function toggleSeat(btn){
  const id = btn.getAttribute('data-id');
  if(btn.classList.contains('booked')) return;
  if(btn.classList.contains('selected')){
    btn.classList.remove('selected');
    selectedSeats = selectedSeats.filter(x => x !== id);
  } else {
    btn.classList.add('selected');
    selectedSeats.push(id);
  }
  document.getElementById('selectedCount').innerText = selectedSeats.length;
  updateTotal();
}

function updateTotal(){
  let price = parseFloat(document.getElementById('seatPrice') ? document.getElementById('seatPrice').innerText : 0);
  document.getElementById('totalPrice').innerText = (price * selectedSeats.length).toFixed(2);
}

function prepareBooking(e){
  e.preventDefault();
  if(selectedSeats.length===0){
    alert('Select at least one seat');
    return;
  }
  document.getElementById('seatIds').value = selectedSeats.join(',');
  document.getElementById('bookingForm').submit();
}
