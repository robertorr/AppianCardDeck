# AppianCardDeck
This project is an implementation of the coding problem given by Appian.  The requirements are given in the included document: `DeckofCardsProblemStatement.pdf`.


## Assumptions and Design Choices
* The requirements from the problem statement have been coded into an `IDeck` interface.  The `Deck` class then implements these requirements.
* I decided to an `ArrayList` (rather than a `LinkedList`, for example) to hold the cards for the deck because:
  * `size()`, `get()`, and `set()` methods run in _O(1)_ time (vs. _O(n)_ time),
  * adding _n_ elements requires _O(n)_ time (vs. _O(n^2)_ time).
* The `dealOneCard()` method removes a card from the `ArrayList` so that the `size()` method reflects the current size of the deck.
  * An alternative would be to mark each card as dealt but leave it in the list but this adds complexity.
* Cards are dealt from the end of the `List` in order to speed up the deal.  If cards were dealt from the start of the `List`, the underlying `ArrayList` would have to shift the cards in its backing array whenever a card was dealt.
  * This decision necessitated changing the `populateDeck()`, `sort()`, and `toString()` methods to work in the 'reverse' direction from usual (e.g., back-to-front).
* An attempt at "designing for extension" was made by leaving the classes _not_ final, and by exposing a couple of protected methods in the deck class
  * The protected methods exposed the `ArrayList` that contains the cards.  A subclass can alter the list as it sees fit (for example, inserting/removing cards at arbitrary locations), or by adding more sets of cards to the deck to make a 'shoe'.
* The `Card` class is immutable.  It is therefore likely thread-safe, but multi-threaded testing would need to be done to ensure that this is true.
* The `Deck` class is _not_ thread-safe.  Probably not much more code would need to be added (a few strategically placed `synchronized` keywords on methods), but to ensure thread-safety much more testing would need to be done.
* All classes have been made `Serializable` in order to persist their instances or send them over the wire.  (Note: `enums` are `Serializable` by default.)


## Ideas for Future Improvement

* Make `Deck` class thread-safe and add requisite tests
* Determine how to measure efficacy of `shuffle()` method.  Perhaps some measure of the _entropy_ of the cards would work. (The sorted deck would have the lowest amount of entropy, etc.)
* Statically construct all possible `Cards` and store in a cache.  A factory method could be added to `Card` to retrieve the requested `Card`.  This is similar to what `java.lang.Integer` does with values between -128 and 127.
* Add the capability to have a trump suit.  The card `Comparator` would need to change to accommodate this.
* Add internationalization (_i18n_) to the various `toString()` methods
* Expose underlying `List` methods to insert and remove cards from arbitrary locations in the `Deck`
* Create a `Hand` class, and add the ability to compare `Hands`
