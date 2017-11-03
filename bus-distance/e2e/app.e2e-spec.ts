import { BusDistancePage } from './app.po';

describe('bus-distance App', () => {
  let page: BusDistancePage;

  beforeEach(() => {
    page = new BusDistancePage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
