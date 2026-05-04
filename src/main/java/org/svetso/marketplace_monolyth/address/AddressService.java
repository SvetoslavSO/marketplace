//package org.svetso.marketplace_monolyth.address;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.svetso.marketplace_monolyth.company.infrastructure.persistence.repository.JpaAddressRepository;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class AddressService {
//
//    private final JpaAddressRepository jpaAddressRepository;
//
//    public Address getOrCreateAddress(String city, String country, String street, String postalCode) {
//        return jpaAddressRepository
//                .findAddressByCityAndCountryAndStreet(city, country, street)
//                .orElseGet(() -> {
//                    Address newAddress = new Address();
//                    newAddress.setCity(city);
//                    newAddress.setCountry(country);
//                    newAddress.setStreet(street);
//                    newAddress.setPostalCode(postalCode);
//                    return jpaAddressRepository.save(newAddress);
//                });
//    }
//}
//
////addressRepository.findAddressByCityAndCountryAndStreet(
////        request.getCity(),
////                        request.getCountry(),
////                        request.getStreet()
////                )
////                        .orElseGet(() -> {
////Address newAddress = new Address();
////                    newAddress.setCity(request.getCity());
////        newAddress.setCountry(request.getCountry());
////        newAddress.setStreet(request.getStreet());
////        return addressRepository.save(newAddress);
////                });