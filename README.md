## Objects:

Auction - auctionId, products, bids, users
<br>
Bid - bidId, amount, bidderId, productId
<br>
Product -  productId, minPrice, seller, sold
<br>
RealEstate - Product + rooms, year, area, balcony, garden, parkingSlot
<br>
Ticket - Product + eventName, eventDate, boughtPrice
<br>
Vehicle - Product + brand, model, km, year, consume
<br>
User - userId, name, address, email, boughtProducts
<br>
AuctionService - simulates an auction
<br>
ProductService - simulates diverse operations on products
<br>
UserService - simulates diverse operations on users

## Interogations:

cheapest - returns cheapest product
<br>
mostExpensive - returns most expensive product
<br>
updateBidAmount - updates bid
<br>
deleteSmallBids - deletes bids smaller than a specified amount
<br>
printBids - prints all bids
<br>
balconyEstates - returns real estates with balcony
<br>
gardenEstates - returns real estates with garden
<br>
bidOTD - returns bid with the biggest amount of the auction
<br>
bidderOTD - returns bidder with the biggest amount of the auction
<br>
setWinners - appends products to winners list of boughtProducts
<br>
updateTicketDate - updates date of a ticket
<br>
updateVehicleConsume - updates consume of a vehicle
<br>
DeleteProductsBySeller - deletes products put on sell by a specific user
<br>
deleteOlderVehicles - deletes Vehicles from a year smaller than a specified year
<br>
deleteExpensiveTickets - deletes Tickets more expensive than a specified price
<br>
printUsers - prints all users
<br>
countItems - counts products bought by an user
<br>
updateAddres - updates address of a user
<br>
banUser - deletes an user